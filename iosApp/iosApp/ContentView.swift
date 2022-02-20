import SwiftUI
import Shared

func greet() -> String {
    return Greeting().greeting()
}

struct ContentView: View {
    var body: some View {
        VStack {
            compositionRoot.createExampleScreen()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
