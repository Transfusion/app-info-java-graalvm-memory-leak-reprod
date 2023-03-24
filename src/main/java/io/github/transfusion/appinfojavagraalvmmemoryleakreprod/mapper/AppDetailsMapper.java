package io.github.transfusion.appinfojavagraalvmmemoryleakreprod.mapper;

import io.github.transfusion.app_info_java_graalvm.AppInfo.IPA;
import io.github.transfusion.appinfojavagraalvmmemoryleakreprod.entities.Ipa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Mapper(
        componentModel = "spring"
)
public abstract class AppDetailsMapper {

//    @Mapping(target = "id", source = "id")
    @Mapping(target = "version", expression = "java( ipa.release_version() )")
    @Mapping(target = "build", expression = "java( ipa.build_version() )")
    @Mapping(target = "uploadDate", expression = "java( java.time.Instant.now() )")
    @Mapping(target = "name", expression = "java( ipa.name() )")
    @Mapping(target = "identifier", expression = "java( ipa.identifier() )")
    @Mapping(target = "sizeBytes", expression = "java( java.math.BigDecimal.valueOf(ipa.size()) )")
//    @Mapping(target = "fileName", source = "fileName")

//    @Mapping(target = "storageCredential", source = "storageCredentialId")

    // then do Ipa-specific fields
    @Mapping(target = "minSdkVersion", expression = "java( ipa.min_sdk_version() )")
    @Mapping(target = "iphone", expression = "java( ipa.iphone() )")
    @Mapping(target = "ipad", expression = "java( ipa.ipad() )")
    @Mapping(target = "universal", expression = "java( ipa.universal() )")
    @Mapping(target = "deviceType", expression = "java( ipa.device_type() )")
    @Mapping(target = "archs", expression = "java( ipa.archs() == null ? null : java.util.Arrays.asList(ipa.archs()) )")
    @Mapping(target = "displayName", expression = "java( ipa.display_name() )")
    @Mapping(target = "releaseType", expression = "java( ipa.release_type() )")
    @Mapping(target = "buildType", expression = "java( ipa.build_type() )")
    @Mapping(target = "devices", source = "ipa", qualifiedByName = "mapPolyglotIPAtoIpaDevices")
    @Mapping(target = "teamName", source = "ipa", qualifiedByName = "mapPolyglotIPAtoIpaTeamName")
    @Mapping(target = "expiredDate", source = "ipa", qualifiedByName = "mapPolyglotIPAtoIpaExpiredDate")
//    @Mapping(target = "plistJson", source = "ipa", qualifiedByName = "infoPlistToJsonNode")
    public abstract Ipa mapPolyglotIPAtoIpa(IPA ipa/*, UUID id, UUID storageCredentialId, String fileName*/);

    @Named("mapPolyglotIPAtoIpaDevices")
    public static List<String> mapPolyglotIPAtoIpaDevices(IPA ipa) {
        List<String> res = null;
        try {
            res = Arrays.asList(ipa.devices());
        } catch (Exception ignored) {
        }
        return res;
    }

    @Named("mapPolyglotIPAtoIpaTeamName")
    public static String mapPolyglotIPAtoIpaTeamName(IPA ipa) {
        String res = null;
        try {
            res = ipa.team_name();
        } catch (Exception ignored) {
        }
        return res;
    }

    @Named("mapPolyglotIPAtoIpaExpiredDate")
    public static Instant mapPolyglotIPAtoIpaExpiredDate(IPA ipa) {
        Instant res = null;
        try {
            res = ipa.expired_date().toInstant();
        } catch (Exception ignored) {
        }
        return res;
    }
}
