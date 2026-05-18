# Test Evidence

## TE-001
- 时间：2026-04-15
- 命令：`mvn test -q`
- 结果：通过（Exit code 0）

## TE-002
- 用例：`AdminAuthControllerTest#should_be_unauthorized_when_access_me_without_login`
- 断言：`GET /admin/auth/me` 返回 `401`

## TE-003
- 用例：`AdminAuthControllerTest#should_be_success_when_login_as_admin_and_query_permissions`
- 断言：管理员登录成功，`GET /admin/permissions/current` 包含 `PERM_USER_ROLE_ASSIGN`

## TE-004
- 用例：`AdminAuthControllerTest#should_be_forbidden_when_auditor_query_admin_users`
- 断言：审计员访问 `GET /admin/users` 返回 `403`
