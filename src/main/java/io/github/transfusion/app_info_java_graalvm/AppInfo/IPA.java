package io.github.transfusion.app_info_java_graalvm.AppInfo;

import io.github.transfusion.app_info_java_graalvm.AbstractPolyglotAdapter;
//import io.github.transfusion.app_info_java_graalvm.AppInfo.ipa_related.Framework;
//import io.github.transfusion.app_info_java_graalvm.AppInfo.ipa_related.Plugin;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import java.time.ZonedDateTime;


public abstract class IPA extends AbstractPolyglotAdapter {

    public static IPA from(Context polyglot, String path) {
        polyglot.eval("ruby", "require 'app-info'");
        return polyglot.eval("ruby", "AppInfo::IPA.new('" + path + "')").as(IPA.class);
    }

    public abstract String file();

    public abstract String info_path();

    public abstract String app_path();

    public abstract Long size();

    public String humanSize() {
        Context ctx = getContext();
        Value callSize = ctx.eval("ruby", "-> recv, arg { recv.size(human_size: arg) }");
        Value res = callSize.execute(getValue(), true);
        return res.asString();
    }

    /**
     * module Platform
     * MACOS = 'macOS'
     * IOS = 'iOS'
     * ANDROID = 'Android'
     * DSYM = 'dSYM'
     * PROGUARD = 'Proguard'
     * end
     *
     * @return a string from the above
     */
    public abstract String os();

    /**
     * alias file_type os
     *
     * @return a string from the above
     */
    public abstract String file_type();

//    Delegators start here
//    def_delegators :info, :iphone?, :ipad?, :universal?, :build_version, :name,
//            :release_version, :identifier, :bundle_id, :display_name,
//            :bundle_name, :min_sdk_version, :min_os_version, :device_type

//    public abstract InfoPlist info();

    public boolean iphone() {
        Context ctx = getContext();
        Value v = ctx.eval("ruby", "-> recv, arg { recv.iphone? }");
        Value res = v.execute(getValue(), true);
        return res.asBoolean();
    }

    public boolean ipad() {
        Context ctx = getContext();
        Value v = ctx.eval("ruby", "-> recv, arg { recv.ipad? }");
        Value res = v.execute(getValue(), true);
        return res.asBoolean();
    }

    public boolean universal() {
        Context ctx = getContext();
        Value v = ctx.eval("ruby", "-> recv, arg { recv.universal? }");
        Value res = v.execute(getValue(), true);
        return res.asBoolean();
    }

    public abstract String build_version();

    public abstract String name();

    public abstract String release_version();

    public abstract String identifier();

    public abstract String bundle_id();

    public abstract String display_name();

    public abstract String bundle_name();

    public abstract String min_sdk_version();

    public abstract String min_os_version();

    public abstract String device_type();


//    def_delegators :mobileprovision, :devices, :team_name, :team_identifier,
//            :profile_name, :expired_date

    /**
     * May return null
     *
     * @return list of device UUIDs
     */
    public abstract String[] devices();

    public abstract String team_name();

    public abstract String team_identifier();

    public abstract String profile_name();

    public abstract ZonedDateTime expired_date();

    public abstract String distribution_name();

    public abstract String release_type();

    public abstract String build_type();

    public abstract String[] archs();

    public abstract String[] architectures();

//    public IPAIconHash[] icons_(boolean uncrush) {
//        Context ctx = getContext();
//        Value lambda = ctx.eval("ruby", "-> recv, arg { recv.icons(uncrush: arg) }");
//
//        Value res = lambda.execute(getValue(), uncrush);
//        return res.as(IPAIconHash[].class);
//    }

    public boolean stored_question() {
        Context ctx = getContext();
        Value v = ctx.eval("ruby", "-> recv { recv.stored? }");
        Value res = v.execute(getValue());
        return res.asBoolean();
    }

//    public abstract Plugin[] plugins();
//
//    public abstract Framework[] frameworks();

//    throws an exception as of Jul. 11 2022
//    def hide_developer_certificates
//      mobileprovision.delete('DeveloperCertificates') if mobileprovision?
//    end

//    public abstract MobileProvision mobileprovision();

    public boolean mobileprovision_question() {
        Context ctx = getContext();
        Value v = ctx.eval("ruby", "-> recv { recv.mobileprovision? }");
        Value res = v.execute(getValue());
        return res.asBoolean();
    }

    public abstract String mobileprovision_path();

    /**
     * Test for nullness with .isNull()
     *
     * @return a hash
     */
    public abstract Value metadata();

    public boolean metadata_question() {
        Context ctx = getContext();
        Value v = ctx.eval("ruby", "-> recv { recv.metadata? }");
        Value res = v.execute(getValue());
        return res.asBoolean();
    }

    public void clear() {
        Context ctx = getContext();
        Value v = ctx.eval("ruby", "-> recv { recv.clear! }");
        v.execute(getValue());
    }
}
