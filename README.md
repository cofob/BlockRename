# ![Logo](/docs/images/logo.png) BlockRename ![Logo](/docs/images/logo.png)
Block and modify renames from anvil.

![Plugin in work](/docs/images/demonstration.png)

## Dependencies
  - Vault
  - Spigot

## Config
```yaml
block:
- some names
- to block here
permissions:
- cofob.blockrename.colored
- cofob.blockrename.bypass
- cofob.blockrename.reload
no_permission: '&4You don''''t have the permission to perform this action!'
blocked_text: 'BLOCKED!'  # The text to which the name of the subject will be replaced
```
