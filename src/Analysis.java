public class Analysis {
    /*
    I used the insurance.csv dataset from Kaggle and selected the first N lines for each test without modifying the contents.
     The results show the expected overall trend for hash tables: insertion, search, and deletion all grow slowly
     as N increases, consistent with an average case of O(1) per operation.
     In every graph, the search and deletion curves stay extremely low and almost linear, which fits the theoretical
     expectation that chained hash tables maintain constant-time performance when the load factor is controlled.
     Insertion is the only curve that climbs more noticeably, especially for larger N, because once the table grows
     past its capacity, rehashing causes a temporary spike. That is exactly what appears in the sorted, shuffled,
     and reversed cases: insertion jumps upward around N=850–1050, reflecting the cost of expanding the table and
     reinserting everything. The ordering of the dataset (sorted vs shuffled vs reversed) had only minor effects,
     which matches the theory because hash tables do not depend on input order. Overall, the empirical runtime curves
     match the theoretical Big-Oh behavior: search and deletion operate in near-constant time, while insertion is
     usually O(1) but occasionally incurs a higher cost when rehashing is triggered.

     */
}
