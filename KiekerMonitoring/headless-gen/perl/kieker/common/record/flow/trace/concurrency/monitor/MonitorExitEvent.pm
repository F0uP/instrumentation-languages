use strict;
use warnings;

package kieker::common::record::flow::trace::concurrency::monitor::MonitorExitEvent;

=head1 NAME

kieker::common::record::flow::trace::concurrency::monitor::MonitorExitEvent 

=head1 SYNOPSIS

 my $record = kieker::common::record::flow::trace::concurrency::monitor::MonitorExitEvent->new($timestamp, $traceId, $orderIndex, $lockId);
 
 $writer->write($record->genoutput());

=head1 DESCRIPTION

Auto-generated structures. See the IRL code.
		
=head1 METHODS

=head2 $record = kieker::common::record::flow::trace::concurrency::monitor::MonitorExitEvent->new($timestamp, $traceId, $orderIndex, $lockId);

Creates a new record with the given parameters.

=cut

sub new {
  my ($timestamp, $traceId, $orderIndex, $lockId) = @_;
  my $this = {
    timestamp => $timestamp,
    traceId => $traceId,
    orderIndex => $orderIndex,
    lockId => $lockId
  };

  return bless($this,$type);
}

=head2 $string = $record->genoutput();

Serializes the record for output. Returns the serialized form of the record.
				
=head1 COPYRIGHT and LICENCE

Copyright 2013 Kieker Project (http://kieker-monitoring.net)

Licensed under the Apache License, Version 2.0 (the "License"); 
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

=cut