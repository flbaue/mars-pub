require_relative './Philosoph.rb'
require_relative './Table.rb'
require 'thread'

seats = 5

table = Table.new seats

philos = []
seats.times { |n| philos << Philosoph.new(table, n) }

philo_threads = []
philos.each do |philo|
	philo_threads << Thread.new {
		philo.think
	}
end

philo_threads.each do |thread|
    thread.join
end