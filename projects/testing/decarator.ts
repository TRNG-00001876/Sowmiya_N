function LogExecutionTime(target:any,propertyKey:string)
  {
     
          const startTime = typeof performance !== 'undefined' ? performance.now() : Date.now();
         
          const endTime = typeof performance !== 'undefined' ? performance.now() : Date.now();
          console.log(`Method ${propertyKey} took ${(endTime-startTime).toFixed(2)} ms`);
     
     
  }
  class MyClass1{
      @LogExecutionTime
      someMethod()
      {
  for(let i=0;i<10;i++)
      {
          console.log("muzamil single always");
      }
      }
  }
  const i=new MyClass1();
  i.someMethod();
