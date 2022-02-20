import Foundation
import Shared

protocol SharedVmWrapper {
    associatedtype MyType: CoreClearable
    var vm: MyType { get }
    
    func onClear()
}

extension SharedVmWrapper {
    func onClear() {
        vm.clear()
    }
}
