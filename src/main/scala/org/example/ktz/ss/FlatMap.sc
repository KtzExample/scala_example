val range = 0 to 5

for {
  ele <- range
  hello = ele +1
} yield hello

range.map(_ + 1).flatMap(List(_))

for {
   ele <- for (ele <- range) yield ele
} yield ele + 1

for {
  ele <- range
  if ele % 2 == 0
} yield ele

Set(1,2,3,4,5,1,1,1,1,1)