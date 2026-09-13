#define ll long long
#include <bits/stdc++.h>
using namespace std;


class ConsistentHashing {
    private:
        // hash -> server
        map<int, string> ring;

        set<int> sorted_keys;

        //number of nodes 
        int virtualNodes;

        int hashFunction(string key){
            cout<<"Hash values generated for "<<key<<" is "<<hash<string> {} (key)<<endl;
            return hash<string> {}(key);
        }

    public:

        ConsistentHashing(int virtualNodes = 3){
            this->virtualNodes = virtualNodes;
        }

        void add_node(string node){
            for(int i = 0; i< virtualNodes; i++){
                int replica_key = hashFunction(node + "_" + to_string(i));
                ring[replica_key] = node;
                sorted_keys.insert(replica_key);
            }
        }

        void remove_node(string node){
            //we have to remove all the replicas of this node
            for(int i = 0; i< virtualNodes; i++){
                int replica_key = hashFunction(node + "_" + to_string(i));
                ring.erase(replica_key);
                sorted_keys.erase(replica_key);
            }
        }

        string get_node(string key){
            if(ring.empty()){
                return "";
            }

            //now we have to find the nearest replica of the node to the key 
            int hash_value = hashFunction(key);
            auto it = sorted_keys.lower_bound(hash_value); //using binary search to find the nearest

            if(it == sorted_keys.end()){
                it = sorted_keys.begin();
            }

            return ring[*it];
        }
};

int main(){
    ConsistentHashing hash_ring;

    hash_ring.add_node("Node_A");
    hash_ring.add_node("Node_B");
    hash_ring.add_node("Node_C");

    string key = "first_key";
    
    hash_ring.remove_node("Node_B");
    hash_ring.remove_node("Node_C");
    hash_ring.remove_node("Node_A");


    hash_ring.add_node("Node_D");

    string node = hash_ring.get_node(key);

    cout << "The key '" << key << "' is mapped to node: " << node << endl;

    return 0;
}