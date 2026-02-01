## 2026-02-01 - Improving accessibility in legacy JSP dashboard
**Learning:** Legacy JSP applications often lack modern accessibility attributes (ARIA) and may contain invalid HTML patterns like nested interactive elements (e.g., `<button>` inside `<a>`).
**Action:** Use `aria-label` for icon-only buttons and checkboxes, and ensure interactive elements are not nested by styling the outer element directly with Bootstrap button classes.
