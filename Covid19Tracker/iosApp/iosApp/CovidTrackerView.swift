import SwiftUI
import shared

struct CovidTrackerView: View {
    
    @StateObject var viewModel = TrackingViewModel()
    
    var body: some View {
        
        NavigationView {
            
            VStack {
                
                if viewModel.isLoading {
                    LoadingIndicator()
                    
                } else {
                    List(viewModel.trackings, id: \.self) { tracking in
                        
                        HStack{
                            Text(tracking.state)
                                .padding()
                                .foregroundColor(Color.white)
                                .background(Color.green)
                                .clipShape(Circle())
                                .font(.custom("", size: 22))
                        }
                        Spacer()
                        VStack(alignment: .center) {
                            
                            Text("\(tracking.total)")
                                .foregroundColor(Color.gray)
                                .font(.custom("", size: 45))
                            
                            HStack(alignment: .center, spacing: 10) {
                                HStack {
                                    Text("☠️")
                                    Text("\(tracking.death)")
                                }.padding(5)
                                .foregroundColor(Color.black)
                                .background(Color.white)
                                .cornerRadius(10)
                                
                                HStack {
                                    Text("🏥")
                                    Text("\(tracking.hospitalized ?? 0)")
                                }.padding(5)
                                .foregroundColor(Color.black)
                                .background(Color.white)
                                .cornerRadius(10)
                            }
                        }
                        
                        Spacer()
                    }
                    .listStyle(GroupedListStyle())
                }
            }
            .toolbar {
                ToolbarItem {
                    Button(action: {
                        viewModel.getTrackings()
                    }, label: {
                        Image(systemName: "arrow.clockwise.circle")
                    })
                }
            }
            .navigationBarTitle("COVID-19 Tracker")
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        CovidTrackerView()
    }
}
