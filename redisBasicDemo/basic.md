#里面是一些使用 StringRedisTemplate的一些基本操作

###  String 类型

###  hash 类型

### list 类型

### set 类型

### sortedSet 类型


##使用 hash结构存储对象是有很大弊端的。
我们要将 对象 转化为 map结构，然后才能使用命令存储
我们在获取对象的时候我们只能先获取一个map结构，将map结构转化为对象，
我们得到的map结构都是String类型，我们要将他转化为 对象。

## 我们的使用StringRedisTemplate , 存储必须是String类型。