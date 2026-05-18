# Role-Permission Matrix

## 权限点定义

- `P-001` `PERM_BOOKING_VIEW`：查看预约记录
- `P-002` `PERM_VIOLATION_VIEW`：查看违约记录
- `P-003` `PERM_BOOKING_CREATE_FOR_USER`：代用户预约
- `P-004` `PERM_BOOKING_CANCEL_FOR_USER`：代用户取消预约
- `P-005` `PERM_SEAT_MANAGE`：座位登记/注销
- `P-006` `PERM_STUDYROOM_MANAGE`：自习室登记/注销
- `P-007` `PERM_SYSTEM_PARAM_MANAGE`：系统参数调整
- `P-008` `PERM_USER_ROLE_ASSIGN`：给管理端用户分配角色

## 角色矩阵

- `ROLE_SUPER_ADMIN`：`P-001~P-008`
- `ROLE_OPERATOR`：`P-001~P-006`
- `ROLE_AUDITOR`：`P-001~P-002`

## 前端展示映射

- 菜单“预约记录”：需要 `P-001`
- 菜单“违约记录”：需要 `P-002`
- 按钮“代预约”：需要 `P-003`
- 按钮“代取消”：需要 `P-004`
- 菜单/按钮“座位管理”：需要 `P-005`
- 菜单/按钮“自习室管理”：需要 `P-006`
- 菜单/按钮“系统参数”：需要 `P-007`
- 菜单/按钮“用户角色分配”：需要 `P-008`
