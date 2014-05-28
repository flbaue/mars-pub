class Philosoph
    
    @@instance_counter = -1
    
    attr_reader :id, :left_stick, :right_stick
    
    def initialize table, seat
        @id = get_id
        @table = table
        @seat = seat
        @left_stick = seat - 1
        @right_stick = seat
        @status = "P#{@seat}: nothing"
    end
    
    def eat
        status "P#{@seat}: taking sticks"
        
        @table.take_sticks self
        
        status "P#{@seat}: eating"
        
        eating_time = rand 5
        sleep eating_time
        
        status "P#{@seat}: putting sticks"
        
        @table.put_sticks self
        
        think
    end
    
    def think
       status "P#{@seat}: thinking"
       
       thinking_time = rand 5
       sleep thinking_time
       
       eat
    end
    
    def to_s
        @status
    end
    
    private 
    
    def status text
       @status = text
       puts @status 
    end
    
    def get_id
        @@instance_counter += 1
        @@instance_counter
    end
    
end


# s0  P1  s1  P2  s2  P3  s3  P4