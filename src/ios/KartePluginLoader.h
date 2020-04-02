//
//  KarteRuntime.h
//  Example
//
//  Created by Tomoki Koga on 2020/04/15.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface KartePluginLoaderInternal : NSObject
+ (void)handleLoad;
@end

@interface KartePluginLoader : KartePluginLoaderInternal
@end

NS_ASSUME_NONNULL_END
