1 @Cacheable(value = "data") 
  redis key为 data::1
  @Cacheable(value = "data", key = "'User-' + #id") 
  redis key为 data::User-1

2 CachingConfigurerSupport 修改默认key

3 如何修改key value 的默认key
4 org.springframework.data.redis.cache.CacheKeyPrefix

先删缓存 再更新数据库 
导致 刚删完，另一个读操作，立马又把更新前数据放到了缓存中

先更新数据库 再删缓存
导致 