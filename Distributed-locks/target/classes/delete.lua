local key =KEYS[1]
local value = KEYS[2]

if redis.call("get",key)==value then
    redis.call("delete",key)
    return true
else
    return false
end