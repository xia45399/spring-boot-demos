1 @Cacheable(value = "data") 
  redis key为 data::1
  @Cacheable(value = "data", key = "'User-' + #id") 
  redis key为 data::User-1

2 CachingConfigurerSupport 修改默认key

3 如何修改key value 的默认key
4 org.springframework.data.redis.cache.CacheKeyPrefix

