# Title:Adview
![スクリーンショット 2021-02-03 20 11 13](https://user-images.githubusercontent.com/63575996/106739160-280e9200-665c-11eb-9634-bed6d41a2524.png)
![ダウンロード](https://user-images.githubusercontent.com/63575996/106738648-88e99a80-665b-11eb-84e3-a0be3c522bc6.gif)
## 概要
オンライン旅行予約サイトです。
閲覧、選択、お気に入り追加、決済、確認、削除をスムーズに行うことができます。

## このテーマを選んだ理由
前職で外国人向けのツアーガイドを行っており、
業務の中で使っていた予約管理ツールのUIとカレンダー機能などに改善できる点があると感じていたため、自らで作ってみようと思ったっため。

## 難しかった点
- FullCalendar.jsを使用してのカレンダーとDBとの連動実装
- N+1問題を解決するためのリファクタリング
- モックを使用したJUnitテスト
- Stripe APIを使用いたクレジットカード決済機能実装

## こだわった点
- 単に不具合のないアプリケーションとして動作するだけでなく、N+1問題の解消や単体試験のメソッド網羅によるリファクタリングを行いパフォーマンを改善した点
- できるだけAjaxを使用し、画面遷移を少なくすることで使いやすさを向上させた点

## 機能一覧
- ユーザ画面
  - 商品
    - 一覧閲覧
    - 検索
    - 詳細ページ表示
      - GoogleMap表示
      - Youtube表示
    - ページング
  - ユーザ
    - 新規ユーザ登録
    - ログイン(Ajax)
    - マイページ
      - アカウント情報表示・修正(Ajax)
      - プロフィール画像編集(Ajax)
  - 予約
    - カレンダー表示
    - カレンダー選択
    - お気に入り追加・確認・削除
    - クレジットカード決済(Stripe API)
    - 予約確認メール送信
    - 予約確認・削除
  - お知らせ
- 管理者画面
  - ログイン
  - メニュー表示
  - アカウント
    - 表示
    - 修正(Ajax)
  - ユーザ情報
    - ページング
    - 一覧表示
  - 商品
    - 一覧表示
    - 選択・全選択削除
  - 予約
    - カレンダー表示
    - リスト表示
  - お知らせ
    - 一覧表示
    - 新規作成

## 使い方
- ユーザー画面(http://13.114.26.30:8080/portfolio). ~~※現在停止中~~
  - 商品表示・検索機能
    1. トップページの「What's popular」から任意の画像を押下する。
    2. ヘッダーのロゴを押下してトップページに遷移した後、「Where To Go」から任意の画像を押下する。
    3. ヘッダーのロゴを押下してトップページに遷移した後、「What To Do」から任意の画像を押下する。
    4. ヘッダーの検索窓に「Tokyo Hokkaido Okinawa」と入力し、「🔍」アイコンを押下する。
    5. 検索結果下部のページ番号を押下する。
  - ユーザー新規登録機能
    1.  ヘッダーの「Sign up」を押下する。
    2.  任意の新規ユーザー名を「User Name」に入力し、[Check availability]ボタンを押下する。
    3. 表示されるアラートの[OK]ボタンを押下する。
    4. その他の入力項目を任意で入力し、[Confirm]ボタンを押下する。
    5. 表示されるアラートの[OK]ボタンを押下して、マイページ画面に遷移する。
  - ログイン/ログアウト機能
    1. 未ログイン状態でヘッダーの「Log in」を押下する。
    2. 「Email」と「Password」に登録したユーザーのものを入力し、[Login]ボタンを押下することでヘッダーのWelcomeメッセージが切り替わる
    3. ログイン状態でヘッダーの「Log out」を押下することでログアウトし、未ログイン状態に切り替わる。
  - マイページ機能
    1. ログイン状態でヘッダーの「My page」を押下し、マイページ画面に遷移する。
    2. [Choose your photo]ボタンを押下し、画像を選択することで表示されるアラートの[OK]ボタンを押下する。
    3. [Modify]ボタンを押下し、プロフィール編集状態に切り替える。
    4. 任意でユーザー情報を編集し、[Submit]ボタンを押下する。
  - カレンダー予約機能
    1. ログイン状態でトップページの「What's popular」から任意の画像を押下して、商品詳細ページに遷移する。
    2. カレンダーから任意の日付を押下する
    3. カレンダー右横の「➕」ボタンを押下して任意の予約人数を入力する。
    4. [Book Now]ボタンを押下し、表示される確認モーダルの[OK]ボタンを押下して決済ページに遷移する。
    5. [Pay with Card]ボタンを押下して、決済モーダルを表示する。
    6. メールドレスに任意のメールアドレス、カード番号に「4242 4242 4242 4242」、カード利用期限に「02/24」、CVCに「234」と入力し、[支払う]を押下し、決済が完了するのを待つ。10秒ほどで決済完了画面に遷移する。
    7. ヘッダーの「Reservation」を押下して、予約確認ページに遷移する。
    8. [Cancel]ボタンを押下し、表示される確認モーダルの[Yes]ボタンを押下し、予約を削除する。
- 管理者画面(http://13.114.26.30:8080/portfolio/admin).  ~~※現在停止中~~
  1. ログインページのUser Nameに「demoAdmin」、Passwordに「password」を入力し、「Login」ボタンを押下して、管理者トップページに遷移する。
  2. メニューの「Account」をクリックして、「管理者アカウント」画面に遷移する。
  3. サイドメニューの「Users」をクリックして「ユーザー管理」画面に遷移する。
  4. サイドメニューの「Products」をクリックして「商品管理」画面に遷移する。最終ページの最後の商品を選択して[Delete]ボタンを押下する。
  5. サイドメニューの「Reservations」をクリックして「予約管理(リスト)」画面に遷移する。「🗓」アイコンをクリックして「予約管理(カレンダー)」画面に切り替える。
  6. サイドメニューの「Testimonials」をクリックして「お客様の声」画面に遷移する。
  7. サイドメニューの「Notices」をクリックして「お知らせ管理」画面に遷移する。[Create]をクリックして、任意でお知らせを作成し、[Submit]をクリックする。

## 使用技術
- Java 11
- Spring Boot 2.4.2
- PostgreSQL 12.5
- HTML 5
- CSS 3
  - Bootstrap 4
- SCSS
- JQuery
  - OwlCarousel.js
  - FullCalendar.js
  - BootBox.js
- Docker
- AWS (ECS/Fargate/RDS)
- Circle CI
- Stripe API
- Google Maps API

## インフラ構成図
![infrastructure](https://user-images.githubusercontent.com/63575996/107296139-26a5f500-6ab4-11eb-9174-28fdb804d381.png)


## 画面設計書
以下のExcelファイルまたはPDFファイルをご参照ください。
- [Adview画面設計書.xlsx](https://github.com/Ryoto-Noguchi/Portfolio-OTA/files/5929104/Adview.xlsx)
- [Adview画面設計書.pdf](https://github.com/Ryoto-Noguchi/Portfolio-OTA/files/5931885/Adview.pdf)
## ER図
![relation](https://user-images.githubusercontent.com/63575996/106725206-7320a900-664c-11eb-87ea-b463cd441fe5.png)

# テスト
- 単体試験
  - JUnit5
  - Mockito
  - h2
  - Circle CI自動テスト
- 結合試験(URL配置予定)
