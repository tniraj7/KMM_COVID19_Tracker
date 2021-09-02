import shared

class TrackingViewModel: ObservableObject {
    
    @Published var isLoading = Bool()
    @Published var trackings: [Tracking] = []
    
    init() {
        getTrackings()
    }
    
    func getTrackings() {
        self.isLoading = true
        CovidTrackingAPI().fetchCovidTrackingData { trackings in
            DispatchQueue.main.async {
                self.isLoading = false
                self.trackings = trackings
            }
        } failure: { throwableException in
            fatalError(throwableException.debugDescription)
        }
    }
}
