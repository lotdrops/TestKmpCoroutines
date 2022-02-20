# Trying kmp coroutines

Project to test using kmp coroutines in Android + iOS codebase.  

The first time the project is setup and every time the shared code changes, the iOS XCFramework needs to be built. This is done with the following command: `./gradlew shared:assembleSharedXCFramework`.  

Currently, the debug framework is linked in the iOS project. To build for release:  
1) Unlink the current one in XCode: in *Frameworks* folder, select *Shared.xcframework* and press the delete key, then select *remove reference*.  
2) Link the release framework: drag the *Shared.xcframework* from `shared/build/XCFrameworks/release/` to the *Frameworks* folder in XCode.  

## Modules  
The Kt module contains the public API of the shared code, that should be consumed by the iOS UI. In shared, all the shared modules are linked through Koin, and provider methods are built to serve as a simple DI for iOS.  
On iOS a swift VM wraps the shared kotlin VM, exposing publishers and methods for the flows and methods of the kotlin VM.   