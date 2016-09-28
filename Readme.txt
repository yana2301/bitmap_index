Implementation Details:
Data is indexed using bitmap indices:
1.for each attribute separate bitset is created. bit in bitset is set to 1 if record has attribute.
2.to find records that have specific set of attributes we find intersection of bitsets for requested attributes.

Complexity estimation:
Let's say we have:
    N records
    M properties for each record
    and query for K attributes.
In this approach we need to perform AND operation K times.
There are also additional operations for taking matching records from the list, but they run in constant time, can be skipped.
Each AND operation is proportional to number of records in dataset - N.
Number of operations for each query is proportional to KN.

This means that complexity depends linearly both from number of records and number of attributes in the query.
It's important that it does not depend on M - number of boolean properties for each record.

Possible optimizations:
BitSets can be very sparse in case of large number of attributes.As a result they consume many memory.
There are better implementations, e.g. roaring bitmaps, some other compression approaches.
In general, for production use code should count more unexpected situations, e.g. empty/incorrect values in file,etc.

Scaling the solution:
1. Horizontal scaling. Bitmap indices can be separated on different machines, e.g. machine A contains bitmaps for records 1- 100,
machine B contains records 100-200. Query can run in parallel on 2 machines, resulting records can be joined into one list.
2. Vertical scaling. After selecting bitmaps to join, we can join them separately. E.g. we have 2 processors, and need to join 100 bitmaps.
We can join 50 bitmaps in one thread and 50 in another and then join partial results additionally. Then select matching records based on set bits.