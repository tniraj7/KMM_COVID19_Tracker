import SwiftUI

struct LoadingIndicator: View {
    var body: some View {
        HStack {
            ProgressView()
                .progressViewStyle(CircularProgressViewStyle())
                .scaleEffect(1.1)
            
            Text("Loading...")
                .padding(.leading, 10)
        }
        .padding()
        .background(Color.black.opacity(0.2))
        .cornerRadius(12)
    }
}
