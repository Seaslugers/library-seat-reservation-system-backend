# Task Audit (2026-04-15)

Audit target: verify real completion status against `record/05-implementation-tasks.md`.

## Result

- `T-001` Done
- `T-002` Done
- `T-003` Partial (missing `PermissionDO` model class)
- `T-010` Done
- `T-011` Done
- `T-012` Done (plain password auto-upgrades to BCrypt on login)
- `T-020` Done
- `T-021` Partial (`@RequirePermission` currently applied on `AdminUserController` only)
- `T-022` Done
- `T-030` Not done
- `T-031` Not done
- `T-032` Not done
- `T-040` Done
- `T-041` Done
- `T-050` Partial (missing login-failure and logout tests)
- `T-051` Done (401/403/200 covered)
- `T-052` Partial (core RBAC path covered, business permission regression pending)

## Evidence

- Command: `mvn test -q` => Exit code `0`
- RBAC tables and seeds exist in `schema.sql` / `data.sql`
- No frontend permission visibility logic found in current pages