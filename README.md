# 说明

suggestion提供类似搜索引擎中对搜索词进行自动完成的功能

核心是使用trie tree保存关键词进行topk查询

# 启动项目
由于使用了java类配置的spring mvc,所以启动环境有如下要求

servlet 3.0 +

tomcat 7 + 或者 jetty 9 +

# 1.持久化相关

使用了mongodb进行持久化，SimpleTriePersist是对整个树进行存取，无法做到部分更新，并且由于mongodb对单个文档有16M的约束，所以不能存过多，过长的关键字。

可以继承TriePersist接口实现自己的持久化

# 2.topk相关

具体实现在DefaultChildrenSearch，其中可以设置任务的超时时间，以及查询的最大深度

可以继承ChildrenSearch实现自己的topk

# 3.保存相关

页面通过一个查询（实际还没有查询功能）按钮进行一次关键字的插入

LocalInsertHandler（用于本地测试）实现了每次插入都马上插入内存的树，并且每5秒对整棵树进行1次保存

可以继承InsertWordHandler实现自己的保存功能


