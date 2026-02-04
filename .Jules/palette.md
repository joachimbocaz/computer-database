## 2026-02-04 - [Dashboard Accessibility and UX Improvements]
**Learning:** Repetitive interactive elements in tables (like checkboxes) should use ARIA labels that include row-specific identifying context (e.g., 'Select computer [Name]') to help screen reader users distinguish between similar actions. Also, manual URL concatenations in JSP are prone to missing delimiters like ampersands.
**Action:** Always check for missing `&` in dynamic URLs and use `<c:out>` for row-specific identifiers in `aria-label` when working with JSP loops.
