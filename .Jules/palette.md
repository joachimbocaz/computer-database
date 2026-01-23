## 2024-07-25 - Icon-only buttons require an `aria-label` for accessibility

**Learning:** I discovered that the icon-only delete button in `dashboard.jsp` was missing an `aria-label` attribute. This is a critical accessibility issue, as it prevents screen reader users from understanding the button's function. Without a text alternative, the button is effectively invisible to them.

**Action:** I will add an `aria-label="Delete selected computers"` to the delete button to provide a clear, descriptive label for screen reader users. This small change will significantly improve the application's accessibility and ensure that all users can interact with the interface effectively. I will also be mindful of this issue in future enhancements.
