## 簡易ERP系統

---

### 系統功能:

- 工做派遣
    - 可自訂義工作內容參數 : 工作名稱、工作優先度、日期、被指派員工、工作內容說明、是否要發布、是否完成
    - 說明 :
        - 總經理可派遣所有部門經理與員工工作。
        - 該部門經理可派遣該部門員工工作，並選擇是否發布。
        - 該部門員工僅能觀看已發布且未完成之工作。
- 人事管理
    - 可自訂義員工內容參數 : 員工帳號、員工密碼、信箱、手機號碼、職位、是否啟用、大頭照
    - 說明:
        - 僅有總經理與人資部門具有編輯員工資料權限。
        - 其餘各部門經理僅能觀看該部門員公編號、名稱、信箱、手機
        - 各部門員工僅能觀看該部門員公編號、名稱、信箱
- 公告發布
    - 可自訂義公告內容參數 : 公告標題、發布者(會依照登入資訊，無法修改)、發布日期、發布內容
    - 說明:
        - 僅有總經理與各部門經理具有發布公告權限。
        - 所有部門皆可看到總經理發送公告
        - 該部門僅能觀看該部門公告與總經理公告
- 簡易會計登入
    - 可自訂義會計內容參數 : 會計科目、日期、金額、詳細說明
    - 說明:
        - 僅有總經理與財務部門具有會計科目編輯權限。
- 數據可視化
    - 會依據各部門工作完成度與公司財務狀況進行數據可視化，幫助使用者快速了解公司狀況
  
_底下有詳細說明_
    
---

### 權限功能表

| 職稱名稱 | 公告發布 |工作派遣 |部門成員查看 |部門成員編輯 |會計科目登記 |
|---------|---------|---------|---------|---------|---------|
|總經理 (GENERAL MANAGER)| O | O | O | O |O |
|人資部經理(HR-MANAGER)|O| O | O | O | |
|人資部員工(HR-USER)| | | O | O | |
|開發部門經理(RD-MANAGER)|O| O | O | |
|開發部員工(RD-USER)| | | O | | |
|財務部經理(FD-MANAGER)|O| O | O | |O |
|財務部員工(FD-USER)| | | O | |O |

---
###功能詳細說明
####總經理


####部門經理-以財務部門為例
可從首頁一覽該部門工作完成度與新增、觀看公告(部門經理、員工只能看見該部門公告與總經理公告，只有總經理有權限跨部門查看)
![]()

財務部門會計可依會計五大類進行獨立作業，也可透過大表一次性管理所有科目。
![](https://github.com/4a7g0018/yan.erp/blob/master/images/FD_Account.gif)

部門工作派遣，派遣完畢後可透過滑動按鈕選擇是否發布與工作是否完成(該部門管理員派遣該部門員工，只有總經理有權限跨部門派遣)
![]()

部門員工查看，該部門只能查看該部門員工聯絡資料，但無法進行修改(只有總經理與人資部有權限進行人資管理)

![]()