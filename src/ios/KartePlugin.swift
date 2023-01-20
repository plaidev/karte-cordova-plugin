import KarteCore
import KarteInAppMessaging
import KarteRemoteNotification

@objc(KartePlugin)
class KartePlugin: CDVPlugin {
    private lazy var variables: KarteVariables = {
        KarteVariables()
    }()
    
    // MARK: KarteCore
    @objc(visitorId:)
    func visitorId(command: CDVInvokedUrlCommand) {
        DispatchQueue.main.async {
            let result = CDVPluginResult(status: .ok, messageAs: KarteApp.visitorId)
            self.commandDelegate.send(result, callbackId: command.callbackId)
        }
    }
    
    @objc(isOptOut:)
    func isOptOut(command: CDVInvokedUrlCommand) {
        DispatchQueue.main.async {
            let result = CDVPluginResult(status: .ok, messageAs: KarteApp.isOptOut)
            self.commandDelegate.send(result, callbackId: command.callbackId)
        }
    }
    
    @objc(optIn:)
    func optIn(command: CDVInvokedUrlCommand) {
        DispatchQueue.main.async {
            KarteApp.optIn()
            self.commandDelegate.send(.noResult, callbackId: command.callbackId)
        }
    }
    
    @objc(optOut:)
    func optOut(command: CDVInvokedUrlCommand) {
        DispatchQueue.main.async {
            KarteApp.optOut()
            self.commandDelegate.send(.noResult, callbackId: command.callbackId)
        }
    }
    
    @objc(renewVisitorId:)
    func renewVisitorId(command: CDVInvokedUrlCommand) {
        DispatchQueue.main.async {
            KarteApp.renewVisitorId()
            self.commandDelegate.send(.noResult, callbackId: command.callbackId)
        }
    }
    
    @objc(registerFCMToken:)
    func registerFCMToken(command: CDVInvokedUrlCommand) {
        DispatchQueue.main.async {
            let fcmToken = command.argument(at: 0) as? String
            KarteApp.registerFCMToken(fcmToken)
            self.commandDelegate.send(.noResult, callbackId: command.callbackId)
        }
    }
    
    override func handleOpenURL(_ notification: Notification!) {
        guard let url = notification.object as? URL else {
            return
        }
        KarteApp.application(UIApplication.shared, open: url)
    }
    
    @objc(appendingQueryParameter:)
    func appendingQueryParameter(command: CDVInvokedUrlCommand) {
        DispatchQueue.main.async {
            guard let url = command.argument(at: 0) as? String else {
                self.commandDelegate.send(.errorMissingArgument, callbackId: command.callbackId)
                return
            }
            
            let result = CDVPluginResult(status: .ok, messageAs: UserSync.appendingQueryParameter(url))
            self.commandDelegate.send(result, callbackId: command.callbackId)
        }
    }

    @objc(getUserSyncScript:)
    func getUserSyncScript(command: CDVInvokedUrlCommand) {
        DispatchQueue.main.async {
            let result = CDVPluginResult(status: .ok, messageAs: UserSync.getUserSyncScript())
            self.commandDelegate.send(result, callbackId: command.callbackId)
        }
    }
    
    // MARK: KarteCore (Tracking)
    @objc(track:)
    func track(command: CDVInvokedUrlCommand) {
        DispatchQueue.main.async {
            guard let name = command.argument(at: 0) as? String else {
                self.commandDelegate.send(.errorMissingArgument, callbackId: command.callbackId)
                return
            }
            let values = command.argument(at: 1) as? [String: Any] ?? [:]
            
            Tracker.track(name, values: JSONConvertibleConverter.convert(values))
            self.commandDelegate.send(.noResult, callbackId: command.callbackId)
        }
    }
    
    @objc(identify:)
    func identify(command: CDVInvokedUrlCommand) {
        DispatchQueue.main.async {
            let values = command.argument(at: 0) as? [String: Any] ?? [:]
            
            Tracker.identify(JSONConvertibleConverter.convert(values))
            self.commandDelegate.send(.noResult, callbackId: command.callbackId)
        }
    }

    @objc(identifyWithUserId:)
    func identifyWithUserId(command: CDVInvokedUrlCommand) {
        DispatchQueue.main.async {
            guard let userId = command.argument(at: 0) as? String else {
                self.commandDelegate.send(.errorMissingArgument, callbackId: command.callbackId)
                return
            }
            let values = command.argument(at: 1) as? [String: Any] ?? [:]

            Tracker.identify(userId, JSONConvertibleConverter.convert(values))
            self.commandDelegate.send(.noResult, callbackId: command.callbackId)
        }
    }

    @objc(attribute:)
    func attribute(command: CDVInvokedUrlCommand) {
        DispatchQueue.main.async {
            let values = command.argument(at: 0) as? [String: Any] ?? [:]

            Tracker.attribute(JSONConvertibleConverter.convert(values))
            self.commandDelegate.send(.noResult, callbackId: command.callbackId)
        }
    }
    
    @objc(view:)
    func view(command: CDVInvokedUrlCommand) {
        DispatchQueue.main.async {
            guard let viewName = command.argument(at: 0) as? String else {
                self.commandDelegate.send(.errorMissingArgument, callbackId: command.callbackId)
                return
            }
            
            let title = command.argument(at: 1) as? String
            let values = command.argument(at: 2) as? [String: Any] ?? [:]
            
            Tracker.view(viewName, title: title, values: JSONConvertibleConverter.convert(values))
            self.commandDelegate.send(.noResult, callbackId: command.callbackId)
        }
    }
    
    // MARK: KarteInAppMessaging
    @objc(isPresenting:)
    func isPresenting(command: CDVInvokedUrlCommand) {
        DispatchQueue.main.async {
            let result = CDVPluginResult(status: .ok, messageAs: InAppMessaging.shared.isPresenting)
            self.commandDelegate.send(result, callbackId: command.callbackId)
        }
    }
    
    @objc(dismiss:)
    func dismiss(command: CDVInvokedUrlCommand) {
        DispatchQueue.main.async {
            InAppMessaging.shared.dismiss()
            self.commandDelegate.send(.noResult, callbackId: command.callbackId)
        }
    }
    
    @objc(suppress:)
    func suppress(command: CDVInvokedUrlCommand) {
        DispatchQueue.main.async {
            InAppMessaging.shared.suppress()
            self.commandDelegate.send(.noResult, callbackId: command.callbackId)
        }
    }
    
    @objc(unsuppress:)
    func unsuppress(command: CDVInvokedUrlCommand) {
        DispatchQueue.main.async {
            InAppMessaging.shared.unsuppress()
            self.commandDelegate.send(.noResult, callbackId: command.callbackId)
        }
    }
    
    // MARK: KarteVariables
    @objc(fetch:)
    func fetch(command: CDVInvokedUrlCommand) {
        DispatchQueue.main.async {
            self.variables.fetch { [weak self] (isSuccessful) in
                let result = isSuccessful ? CDVPluginResult(status: .ok) : CDVPluginResult(status: .error, messageAs: "Failed to fetch.")
                self?.commandDelegate.send(result, callbackId: command.callbackId)
            }
        }
    }
    
    @objc(variable:)
    func variable(command: CDVInvokedUrlCommand) {
        DispatchQueue.main.async {
            guard let key = command.argument(at: 0) as? String else {
                self.commandDelegate.send(.errorMissingArgument, callbackId: command.callbackId)
                return
            }
            
            let variable = self.variables.variable(forKey: key)
            let result = CDVPluginResult(status: .ok, messageAs: variable.name)
            self.commandDelegate.send(result, callbackId: command.callbackId)
        }
    }
    
    @objc(trackOpen:)
    func trackOpen(command: CDVInvokedUrlCommand) {
        DispatchQueue.main.async {
            guard let keys = command.argument(at: 0) as? [String] else {
                self.commandDelegate.send(.errorMissingArgument, callbackId: command.callbackId)
                return
            }
            let values = command.argument(at: 1) as? [String: Any] ?? [:]
            self.variables.trackOpen(forKeys: keys, values: values)
            self.commandDelegate.send(.noResult, callbackId: command.callbackId)
        }
    }
    
    @objc(trackClick:)
    func trackClick(command: CDVInvokedUrlCommand) {
        DispatchQueue.main.async {
            guard let keys = command.argument(at: 0) as? [String] else {
                self.commandDelegate.send(.errorMissingArgument, callbackId: command.callbackId)
                return
            }
            let values = command.argument(at: 1) as? [String: Any] ?? [:]
            self.variables.trackClick(forKeys: keys, values: values)
            self.commandDelegate.send(.noResult, callbackId: command.callbackId)
        }
    }
    
    // MARK: KarteVariable
    @objc(string:)
    func string(command: CDVInvokedUrlCommand) {
        DispatchQueue.main.async {
            guard let key = command.argument(at: 0) as? String else {
                self.commandDelegate.send(.errorMissingArgument, callbackId: command.callbackId)
                return
            }
            
            let string: String?
            if let value = command.argument(at: 1) as? String {
                string = self.variables.string(forKey: key, default: value)
            } else {
                string = self.variables.string(forKey: key)
            }
            
            let result = CDVPluginResult(status: .ok, messageAs: string)
            self.commandDelegate.send(result, callbackId: command.callbackId)
        }
    }
    
    @objc(integer:)
    func integer(command: CDVInvokedUrlCommand) {
        DispatchQueue.main.async {
            guard let key = command.argument(at: 0) as? String else {
                self.commandDelegate.send(.errorMissingArgument, callbackId: command.callbackId)
                return
            }
            guard let value = command.argument(at: 1) as? Int else {
                self.commandDelegate.send(.errorMissingArgument, callbackId: command.callbackId)
                return
            }
            
            let result = CDVPluginResult(status: .ok, messageAs: self.variables.integer(forKey: key, default: value))
            self.commandDelegate.send(result, callbackId: command.callbackId)
        }
    }
    
    @objc(double:)
    func double(command: CDVInvokedUrlCommand) {
        DispatchQueue.main.async {
            guard let key = command.argument(at: 0) as? String else {
                self.commandDelegate.send(.errorMissingArgument, callbackId: command.callbackId)
                return
            }
            guard let value = command.argument(at: 1) as? Double else {
                self.commandDelegate.send(.errorMissingArgument, callbackId: command.callbackId)
                return
            }
            
            let result = CDVPluginResult(status: .ok, messageAs: self.variables.double(forKey: key, default: value))
            self.commandDelegate.send(result, callbackId: command.callbackId)
        }
    }
    
    @objc(bool:)
    func bool(command: CDVInvokedUrlCommand) {
        DispatchQueue.main.async {
            guard let key = command.argument(at: 0) as? String else {
                self.commandDelegate.send(.errorMissingArgument, callbackId: command.callbackId)
                return
            }
            guard let value = command.argument(at: 1) as? Bool else {
                self.commandDelegate.send(.errorMissingArgument, callbackId: command.callbackId)
                return
            }
            
            let result = CDVPluginResult(status: .ok, messageAs: self.variables.bool(forKey: key, default: value))
            self.commandDelegate.send(result, callbackId: command.callbackId)
        }
    }
    
    @objc(array:)
    func array(command: CDVInvokedUrlCommand) {
        DispatchQueue.main.async {
            guard let key = command.argument(at: 0) as? String else {
                self.commandDelegate.send(.errorMissingArgument, callbackId: command.callbackId)
                return
            }
            
            let array: [Any]?
            if let value = command.argument(at: 1) as? [Any] {
                array = self.variables.array(forKey: key, default: value)
            } else {
                array = self.variables.array(forKey: key)
            }
            
            let result = CDVPluginResult(status: .ok, messageAs: array)
            self.commandDelegate.send(result, callbackId: command.callbackId)
        }
    }
    
    @objc(object:)
    func object(command: CDVInvokedUrlCommand) {
        DispatchQueue.main.async {
            guard let key = command.argument(at: 0) as? String else {
                self.commandDelegate.send(.errorMissingArgument, callbackId: command.callbackId)
                return
            }
            
            let object: [String: Any]?
            if let value = command.argument(at: 1) as? [String: Any] {
                object = self.variables.object(forKey: key, default: value)
            } else {
                object = self.variables.object(forKey: key)
            }
            
            let result = CDVPluginResult(status: .ok, messageAs: object)
            self.commandDelegate.send(result, callbackId: command.callbackId)
        }
    }
}

extension KartePlugin: Library {
    static var name: String {
        "cordova"
    }
    
    static var version: String {
        "0.0.1"
    }
    
    static var isPublic: Bool {
        true
    }
    
    static func configure(app: KarteApp) {
    }
    
    static func unconfigure(app: KarteApp) {
    }
}

extension KartePluginLoader {
    open override class func handleLoad() {
        KarteApp.register(library: KartePlugin.self)
    }
}

private extension CDVPluginResult {
    
    static var noResult: CDVPluginResult {
        CDVPluginResult()
    }
    
    static var errorMissingArgument: CDVPluginResult {
        CDVPluginResult(status: .error, messageAs: "Argument is missing.")
    }
}
