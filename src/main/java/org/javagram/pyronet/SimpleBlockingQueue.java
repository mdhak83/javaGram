/*
 * Created on 12 feb 2010
 */

package org.javagram.pyronet;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class SimpleBlockingQueue<T>
{
   private static final Object    NULL_VALUE = new Object();

   private final BlockingQueue<T> backing;
   private final int capacity;

   public SimpleBlockingQueue()
   {
      this(new LinkedBlockingQueue<T>(), Integer.MAX_VALUE);
   }

   public SimpleBlockingQueue(int cap)
   {
      this(new ArrayBlockingQueue<T>(cap), cap);
   }

   private SimpleBlockingQueue(BlockingQueue<T> backing, int capacity)
   {
      this.backing = backing;
      this.capacity = capacity;
   }

   //

   public void clear()
   {
      this.backing.clear();
   }

   public boolean isEmpty()
   {
      return this.backing.isEmpty();
   }

   public int size()
   {
      return this.backing.size();
   }
   
   public int capacity()
   {
      return this.capacity;
   }

   public void put(T item)
   {
      if (item == null)
      {
         item = (T) NULL_VALUE;
      }

      for (;;)
      {
         try
         {
            this.backing.put(item);
         }
         catch (InterruptedException exc)
         {
            continue;
         }

         break;
      }
   }

   public T peek()
   {
      return this.backing.peek();
   }

   public T poll()
   {
      return this.backing.poll();
   }

   public T poll(long ms)
   {
      try
      {
         return this.backing.poll(ms, TimeUnit.MILLISECONDS);
      }
      catch (InterruptedException exc)
      {
         return null;
      }
   }

   public T take()
   {
      for (;;)
      {
         try
         {
            T item = this.backing.take();
            
            return (item == NULL_VALUE) ? null : item;
         }
         catch (InterruptedException exc)
         {
            continue;
         }
      }
   }
}
