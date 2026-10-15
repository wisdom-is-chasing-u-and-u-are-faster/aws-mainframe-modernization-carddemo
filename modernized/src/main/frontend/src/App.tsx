import React, { useState } from 'react';
import { TerminalLayout } from './components/TerminalLayout';

export const App: React.FC = () => {
  const [currentScreen, setCurrentScreen] = useState<'LOGIN' | 'MENU' | 'ACCOUNT' | 'CARDS'>('MENU');
  const [message, setMessage] = useState('Type option and press ENTER');

  const handlePfKey = (key: string) => {
    if (key === 'F3') {
      setCurrentScreen('MENU');
      setMessage('Returned to Main Menu.');
    }
  };

  return (
    <TerminalLayout title="Main Navigation" tranId="CC00" onPfKey={handlePfKey}>
      <div style={{ lineHeight: '2rem' }}>
        <p>1. Account Management (COACTUPC / COACTVWC)</p>
        <p>2. Credit Card Portfolio (COCRDLIC / COCRDUPC)</p>
        <p>3. Transaction History (COTRN00C / COTRN01C)</p>
        <p>4. Bill Payment & Settlement (COBIL00C)</p>
        <p>5. System Administration (COADM01C / COUSR00C)</p>
        <div style={{ marginTop: '2rem', color: '#ffb000' }}>
          STATUS: {message}
        </div>
      </div>
    </TerminalLayout>
  );
};
export default App;
