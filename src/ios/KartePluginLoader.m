//
//  KarteRuntime.m
//  Example
//
//  Created by Tomoki Koga on 2020/04/15.
//

#import "KartePluginLoader.h"

@implementation KartePluginLoaderInternal

+ (void)handleLoad
{
}

@end

@implementation KartePluginLoader

+ (void)load
{
    [super load];
    [self handleLoad];
}

@end
