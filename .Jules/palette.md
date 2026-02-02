## 2026-02-02 - [Fixing Nested Interactive Elements and Improving Affordance]
**Learning:** Found a recurring pattern of nesting `<button>` tags inside `<a>` tags in the pagination UI, which is invalid HTML and breaks screen reader behavior. Additionally, interactive table headers lacked `cursor: pointer`, reducing discoverability of sorting features.
**Action:** Always style the `<a>` tag directly using Bootstrap button classes (`btn`, `btn-default`) instead of nesting a `<button>`. Use `style="cursor: pointer;"` on `<th>` elements with `onclick` handlers to improve visual affordance.
