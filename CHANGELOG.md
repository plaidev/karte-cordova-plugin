## 0.0.3 - xxxx.xx.xx

## 0.0.2 - 2022.02.02

**🎉FEATURE**

- 依存するKARTE SDKのバージョンを2.x系の最新版に変更しました。
- UserSync
  - WebView連携のための補助APIとして UserSync.getUserSyncScript を追加しました。
    - 返されるスクリプトをWebViewで実行することでユーザー連携が可能になります。
    - これに伴い、クエリパラメータ連携API UserSync.appendingQueryParameter は非推奨になります。
- Tracker
  - attributeイベントを送信するためのAPIを追加しました。
  - `identifyWithUserId` APIを追加しました。

## 0.0.1 - 2020.04.27

初回リリース
