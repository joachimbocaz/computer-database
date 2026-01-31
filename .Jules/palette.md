## 2026-01-31 - [Accessibility and HTML validity in dashboard.jsp]
**Learning:** Nested interactive elements (like a `<button>` inside an `<a>`) are invalid HTML and can cause issues with screen readers and keyboard navigation. Additionally, icon-only buttons and checkboxes without labels are major accessibility hurdles.
**Action:** Always check for nested interactive elements and ensure all inputs/icon-only buttons have proper labels or `aria-label` attributes. Use `sr-only` for labels that should only be accessible to screen readers.
