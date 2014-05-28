require "monitor"

class Table
    
    
    def initialize seats = 0, logger = nil
        @seats = seats
        @sticks_free = []
        @cond = []
        @monitor = Monitor.new
        @seats.times { @sticks_free << true; @cond << @monitor.new_cond }
        @logger = logger
    end
    
    def take_sticks philo
        @monitor.synchronize do
            while @sticks_free[philo.left_stick] != true or @sticks_free[philo.right_stick] != true
               @cond[philo.id].wait 
            end
            @sticks_free[philo.left_stick] = philo
            @sticks_free[philo.right_stick] = philo
            check
        end
    end
    
    def put_sticks philo
        @monitor.synchronize do
            @sticks_free[philo.left_stick] = true
            @sticks_free[philo.right_stick] = true
            @cond[left_seat(philo.id)].signal
            @cond[right_seat(philo.id)].signal
            check
        end
    end
        
    def to_s
        text = ''
        @steats.times { |n| text += "s0:#{@sticks_free[n]}\t" }
    end
    
private

    def check
        
    end

    def left_seat seat
        if seat == 0
            @seats - 1
        else
            seat - 1
        end
    end
    
    def right_seat seat
        if seat == @seats - 1
            0
        else
            seat + 1
        end
    end
end

# stick index
# s0  P0  s1  P1  s2  P2  s3  P3