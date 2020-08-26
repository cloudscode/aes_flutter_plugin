#import "AesflutterpluginPlugin.h"
#if __has_include(<aesflutterplugin/aesflutterplugin-Swift.h>)
#import <aesflutterplugin/aesflutterplugin-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "aesflutterplugin-Swift.h"
#endif

@implementation AesflutterpluginPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftAesflutterpluginPlugin registerWithRegistrar:registrar];
}
@end
