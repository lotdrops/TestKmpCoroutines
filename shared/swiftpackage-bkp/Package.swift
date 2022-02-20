// swift-tools-version:5.3
import PackageDescription

let package = Package(
    name: "KMMBase",
    platforms: [
        .iOS(.v13)
    ],
    products: [
        .library(
            name: "KMMBase",
            targets: ["KMMBase"]
        ),
    ],
    targets: [
        .binaryTarget(
            name: "KMMBase",
            path: "./KMMBase.xcframework"
        ),
    ]
)
