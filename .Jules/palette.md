## 2026-02-06 - [Visual Affordance and A11y on Sortable Headers]
**Learning:** Legacy tables using `onclick` on `<th>` tags for sorting lack default pointer cursors and keyboard accessibility. Adding `style="cursor: pointer;"` provides immediate visual affordance for mouse users, though full a11y requires `tabindex` and key event handlers.
**Action:** Always check sortable headers for `cursor: pointer` and `aria-label` or `role="button"` when working with legacy tables.
