import Foundation
import KarteCore
import KarteVariables

class KarteVariables {
    private var variables: [String: Variable] = [:]
    
    func fetch(completion: FetchCompletion?) {
        Variables.fetch { (isSuccessful) in
            if isSuccessful {
                self.variables.removeAll()
            }
            completion?(isSuccessful)
        }
    }
    
    func variable(forKey key: String) -> Variable {
        let variable = Variables.variable(forKey: key)
        variables[key] = variable
        return variable
    }
    
    func trackOpen(forKeys keys: [String], values: [String: Any]) {
        let variables = keys.compactMap { (key) -> Variable? in
            self.variables[key]
        }
        Tracker.trackOpen(variables: variables, values: JSONConvertibleConverter.convert(values))
    }
    
    func trackClick(forKeys keys: [String], values: [String: Any]) {
        let variables = keys.compactMap { (key) -> Variable? in
            self.variables[key]
        }
        Tracker.trackClick(variables: variables, values: JSONConvertibleConverter.convert(values))
    }
    
    func string(forKey key: String) -> String? {
        guard let variable = variables[key] else {
            return nil
        }
        return variable.string
    }
    
    func string(forKey key: String, default value: String) -> String {
        guard let variable = variables[key] else {
            return value
        }
        return variable.string(default: value)
    }
    
    func integer(forKey key: String, default value: Int) -> Int {
        guard let variable = variables[key] else {
            return value
        }
        return variable.integer(default: value)
    }
    
    func double(forKey key: String, default value: Double) -> Double {
        guard let variable = variables[key] else {
            return value
        }
        return variable.double(default: value)
    }

    func bool(forKey key: String, default value: Bool) -> Bool {
        guard let variable = variables[key] else {
            return value
        }
        return variable.bool(default: value)
    }

    func array(forKey key: String) -> [Any]? {
        guard let variable = variables[key] else {
            return nil
        }
        return variable.array
    }
    
    func array(forKey key: String, default value: [Any]) -> [Any] {
        guard let variable = variables[key] else {
            return value
        }
        return variable.array(default: value)
    }
    
    func object(forKey key: String) -> [String: Any]? {
        guard let variable = variables[key] else {
            return nil
        }
        return variable.dictionary
    }

    func object(forKey key: String, default value: [String: Any]) -> [String: Any] {
        guard let variable = variables[key] else {
            return value
        }
        return variable.dictionary(default: value)
    }
}
