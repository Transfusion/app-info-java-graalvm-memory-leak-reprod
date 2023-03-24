package io.github.transfusion.appinfojavagraalvmmemoryleakreprod;

import io.github.transfusion.app_info_java_graalvm.AbstractPolyglotAdapter;
import io.github.transfusion.app_info_java_graalvm.AppInfo.AppInfo;
import io.github.transfusion.app_info_java_graalvm.AppInfo.IPA;
import io.github.transfusion.appinfojavagraalvmmemoryleakreprod.entities.Ipa;
import io.github.transfusion.appinfojavagraalvmmemoryleakreprod.mapper.AppDetailsMapper;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.net.URL;
import java.util.List;

@SpringBootApplication
public class AppInfoJavaGraalvmMemoryLeakReprodApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppInfoJavaGraalvmMemoryLeakReprodApplication.class, args);
    }
    static String monkeyPatchHash = "class Hash\n" +
            "  def to_utf8\n" +
            "    Hash[\n" +
            "      self.collect do |k, v|\n" +
            "        if (v.respond_to?(:to_utf8))\n" +
            "          [ k, v.to_utf8 ]\n" +
            "        elsif (v.respond_to?(:force_encoding))\n" +
            "          [ k, v.dup.force_encoding('UTF-8') ]\n" +
            "        else\n" +
            "          [ k, v ]\n" +
            "        end\n" +
            "      end\n" +
            "    ]\n" +
            "  end\n" +
            "end";

    static Source[] sources = {
            Source.create("ruby", "Encoding.default_external = 'ISO-8859-1'"),
            Source.create("ruby", "require 'app-info'"),
            Source.create("ruby", "require 'json'"),
            Source.create("ruby", monkeyPatchHash)
    };

    Context newPolyglotContext() {
        Context ctx = Context.newBuilder().allowAllAccess(true).build();
        for (Source source : sources) ctx.eval(source);
        return ctx;
    }

    @Autowired
    private AppDetailsMapper appDetailsMapper;

    void testParseLargeIPA() throws InterruptedException {
        URL url = getClass().getClassLoader().getResource("apps/YouTube_17.43.1_1668160730.ipa");

        Context polyglotCtx = newPolyglotContext();
        AppInfo appInfo = AppInfo.getInstance(polyglotCtx);
        AbstractPolyglotAdapter data = appInfo.parse_(url.getPath());
        Ipa ipa = appDetailsMapper.mapPolyglotIPAtoIpa((IPA) data);
        ((IPA) data).clear();
        polyglotCtx.eval("ruby", "GC.start");
        polyglotCtx.close();

        System.out.printf("parse completed %s %s%n", ipa.getName(), ipa.getVersion());
        System.gc();
        System.runFinalization();
        Thread.sleep(2000);
    }

    void testParseLargeIPAThreeAttributes() throws InterruptedException {
        URL url = getClass().getClassLoader().getResource("apps/YouTube_17.43.1_1668160730.ipa");
        try (Context ctx = Context.newBuilder().allowAllAccess(true).build()) {
            ctx.eval("ruby", "require 'app-info'");
            Value appInfoGem = ctx.eval("ruby", "AppInfo");
            Value ipaObject = appInfoGem.getMember("parse").execute(url.getPath());
            String name = ipaObject.getMember("name").execute().asString(); // "YouTube"
            String version = ipaObject.getMember("build_version").execute().asString(); // "17.43.1"
            String team_name = ipaObject.getMember("team_name").execute().asString();
            List<String> devices = ipaObject.getMember("devices").execute().as(List.class);
            ipaObject.getMember("clear!").executeVoid();
            ctx.eval("ruby", "GC.start");
            System.out.printf("parse completed %s %s%n", name, version);
        }
        System.gc();
        System.runFinalization();
        Thread.sleep(2000);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("iter " + i);
                testParseLargeIPA(); // my actual use case.
//                testParseLargeIPAThreeAttributes(); // memory is leaked at a slower, albeit still very consistent rate if fewer polyglot members are accessed.
            }
        };
    }
}
