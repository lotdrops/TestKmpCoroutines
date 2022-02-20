import Foundation

extension AsyncThrowingStream {
    func receiveValues(handling: @escaping (Element) -> Void) -> Task<(), Never> {
        return Task {
            do {
                for try await data in self {
                    DispatchQueue.main.async() {
                        handling(data)
                    }
                }
            } catch {
                print("Stream handling failed with error: \(error)")
            }
        }
    }
}
