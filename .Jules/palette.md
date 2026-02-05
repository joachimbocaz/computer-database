## 2026-02-05 - Fixing Nested Interactive Elements in JSP Pagination
**Learning:** In legacy JSP/Bootstrap applications, it's common to see `<button>` elements nested inside `<a>` tags for styling. This is invalid HTML and confuses screen readers as both are interactive elements.
**Action:** Remove the nested `<button>` and apply Bootstrap button classes (`btn`, `btn-default`, etc.) directly to the `<a>` element.

## 2026-02-05 - Table Row Action Accessibility
**Learning:** Checkboxes in a table that perform row-specific actions (like selection for deletion) are often unlabeled or poorly labeled (e.g., just "Select").
**Action:** Use `aria-label` with dynamic content, such as `Select computer <c:out value="${computer.name}"/>`, to provide clear context for screen reader users.
