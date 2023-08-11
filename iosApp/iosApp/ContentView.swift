import SwiftUI
import shared

struct ContentView: View {
	var body: some View {
        ComposeView()
            .ignoresSafeArea(.keyboard)
            .edgesIgnoringSafeArea(.bottom)
	}
}
