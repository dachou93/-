using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Assets.scripts.tools
{
    public class ListAndList
    {
        public List<object> list;
        public ListAndList()
        {
            list = new List<object>();
        }

        public void AddList(object obj)
        {
            list.Add(obj);
        }
    }
}
