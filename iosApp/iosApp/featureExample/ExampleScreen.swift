import SwiftUI

struct ExampleScreen: View {
    @ObservedObject var vm: ExampleVMiOS
    init(vm: ExampleVMiOS) {
        self.vm = vm
    }
    
    var body: some View {
        ExampleScreenView(greeting: greet(), useCaseText: vm.data)
            .onAppear {
                vm.onLoad()
            }.onDisappear {
                vm.onClear()
            }
    }
}

private struct ExampleScreenView: View {
    let greeting: String
    let useCaseText: String
    init(greeting: String, useCaseText: String) {
        self.greeting = greeting
        self.useCaseText = useCaseText
    }
    var body: some View {
        VStack {
            Text(greeting)
            Text("Use case text: \(useCaseText)")
        }
    }
}

struct ExampleScreen_Previews: PreviewProvider {
    static var previews: some View {
        ExampleScreenView(greeting: "Hello greeting", useCaseText: "Hardcoded preview text")
    }
}
