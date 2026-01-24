## 2024-07-23 - Add ARIA label to icon-only button
**Learning:** Icon-only buttons are ambiguous to screen reader users without an `aria-label`. This is a critical accessibility issue that prevents users from understanding the button's function.
**Action:** Always check for and add descriptive `aria-label` attributes to any interactive element that does not have a visible text label. In this application, a `<a>` tag with a Font Awesome icon was used to create a button, so the `aria-label` is necessary.
