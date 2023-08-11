import SwiftUI

@main
struct iOSApp: App {
	var body: some Scene {
		WindowGroup {
            ContentView()
        }
	}
}

//import SwiftUI
//import shared
//
//@UIApplicationMain
//class AppDelegate: UIResponder, UIApplicationDelegate {
//    var window: UIWindow?
//
//    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
//        window = UIWindow(frame: UIScreen.main.bounds)
//        let mainViewController = MainViewControllerKt.MainViewController()
//        window?.rootViewController = mainViewController
//        window?.makeKeyAndVisible()
//        return true
//    }
//}
