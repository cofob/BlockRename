[![](https://ci.cofob.ru/job/BlockRename/job/Build/badge/icon?style=plastic)](https://ci.cofob.ru/job/BlockRename/job/Build/lastBuild/)

# ![Logo](https://git.cofob.ru/cofob/BlockRename/raw/branch/master/docs/images/logo.png) BlockRename ![Logo](https://git.cofob.ru/cofob/BlockRename/raw/branch/master/docs/images/logo.png)
Block and modify renames from anvil!

![Plugin in work](https://git.cofob.ru/cofob/BlockRename/raw/branch/master/docs/images/demonstration.png)

## Download
You can download binary from [releases page](https://github.com/cofob/BlockRename/releases).

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
