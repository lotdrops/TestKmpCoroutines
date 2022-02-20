import Shared
import KMPNativeCoroutinesAsync


class ExampleVMiOS: SharedVmWrapper, ObservableObject {
    @Published var data: String = ""
    let vm: KtExampleViewModel
    
    private var getDataTask: Task<(), Never>? = nil
    
    init(vm: KtExampleViewModel) {
        self.vm = vm
    }
    
    func onLoad() {
        getDataTask = asyncStream(for: vm.dataNative).receiveValues { [weak self] data in
            self?.data = data
        }
    }
    
    func onClear() {
        getDataTask?.cancel()
    }
}
