# API Draft

## 认证接口

- `A-001` `POST /admin/auth/login`
  - 入参：`username`, `password`
  - 出参：当前用户基本信息、角色列表、权限码列表
  - 说明：登录成功写入 Session

- `A-002` `POST /admin/auth/logout`
  - 说明：销毁 Session

- `A-003` `GET /admin/auth/me`
  - 说明：获取当前登录用户信息 + 权限列表

## 用户角色分配接口

- `A-010` `GET /admin/users`
  - 说明：查询管理端用户（基础信息 + 角色）

- `A-011` `PUT /admin/users/{userId}/roles`
  - 入参：角色 code 列表
  - 权限：`PERM_USER_ROLE_ASSIGN`

## 权限查询接口（前端展示）

- `A-020` `GET /admin/permissions/current`
  - 说明：返回当前用户权限码集合

## 业务接口权限映射（规划）

- `A-101` `GET /admin/bookings` -> `PERM_BOOKING_VIEW`
- `A-102` `GET /admin/violations` -> `PERM_VIOLATION_VIEW`
- `A-103` `POST /admin/bookings` -> `PERM_BOOKING_CREATE_FOR_USER`
- `A-104` `DELETE /admin/bookings/{id}` -> `PERM_BOOKING_CANCEL_FOR_USER`
- `A-105` `POST/PUT/DELETE /admin/seats` -> `PERM_SEAT_MANAGE`
- `A-106` `POST/PUT/DELETE /admin/studyrooms` -> `PERM_STUDYROOM_MANAGE`
- `A-107` `PUT /admin/system-params` -> `PERM_SYSTEM_PARAM_MANAGE`

## 错误码约定

- 未登录：`401 UNAUTHORIZED`
- 无权限：`403 FORBIDDEN`
- 参数错误：`400 BAD REQUEST`
