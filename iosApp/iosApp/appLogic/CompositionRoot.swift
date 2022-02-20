import Foundation
import Shared

func initDependencies() {
    depsProvider = KoinIOSKt.doInitDependenciesIos()
}

private var depsProvider: DependenciesProvider? = nil
let compositionRoot: CompositionRoot = CompositionRoot(depsProvider: depsProvider!)

class CompositionRoot {
    private let depsProvider: DependenciesProvider
    init (depsProvider: DependenciesProvider) {
        self.depsProvider = depsProvider
    }
    func createExampleScreen() -> ExampleScreen {
        let scope = depsProvider.provideExampleScope(navParams: "")
        return ExampleScreen(vm: ExampleVMiOS(vm: scope.provideExampleViewModel()))
    }
}
