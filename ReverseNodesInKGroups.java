
//TC O(N) SC - O(N/k)
public class ReverseNodesInKGroups {
    public boolean kNodesExistOrNot(ListNode head,int k){
        ListNode temp =  head;
        while(temp!=null && k>0){
            k--;
            temp=temp.next;
        }
        return k==0;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if(head==null || !kNodesExistOrNot(head,k))
            return head;
        ListNode prev=null,nxt=null,curr=head;
        int count=0;

        while(count<k){
            nxt = curr.next;
            curr.next = prev;
            prev=curr;
            curr=nxt;
            count++;
        }

        head.next = reverseKGroup(nxt,k);

        return prev;
    }
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
