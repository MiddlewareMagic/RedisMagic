# K-V 存储模块

redis 做的事情,就是将看起来简单的事情, 做到一种极致.

- 固定大小 k-v 缓存 (自动淘汰策略)

- expire 定时淘汰策略 定时淘汰, 记录淘汰时间, 惰性淘汰 最后的实现方案是什么? 没太理解

- 持久化策略 (重启数据不丢失)

- remove Listener 删除监听器

- 慢日志监控 slow Listener

- AOF 持久化策略 实现方式 变更内容才会调用 aof 方法 使用注解对目标方法进行增强, 保证指定的操作才会进行 持久化处理

- LRU/LFU 策略详解 

  - LRU: Least Recently Used 最近最少使用
  - 如果数据最近被访问过, 其将来被访问的几率也更高
  - 实现方式:
    - 新数据插入链表头部
    - 每当缓存命中时, 将数据转移到链表头部
    - 链表满时, 将链表尾部数据丢弃
  - 最终使用 基于双向列表实现
  - 借助 LinkedHashMap 实现
  - 如何避免热点数据被 批处理数据污染?
  - LRU基本思想: 局部性原理的 时间局部性: 
    - 如果一个信息项正在被访问, 那么在近期它很可能还会被再次访问
  - 当存在热点数据时, LRU 效率很高, 但 偶发性\周期性的批量操作会导致 LRU命中率急剧下降, 缓存污染情况严重

- LRU 算法变种: LRU-K, 解决缓存污染问题

- Tow Queues 算法

- Multi Queue 算法

  

- LFU Least Frequently Used 最近最不常用

  - O(n)  hashmap 记录次数
  - O(log n) 小顶堆+hashmap
  - O(1) 为 LFU 设计的算法

- CLOCK 算法

  - 最近未用算法(Not Recently Used)

- expaire 随机过期

- 

```
Apache Commons LRUMAP 源码详解

Redis 当做 LRU MAP 使用

java 从零开始手写 redis（七）redis LRU 驱除策略详解及实现
```

